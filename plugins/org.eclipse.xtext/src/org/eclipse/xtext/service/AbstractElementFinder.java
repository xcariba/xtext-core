/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.IGrammarAccess.IEnumRuleAccess;
import org.eclipse.xtext.IGrammarAccess.IParserRuleAccess;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.Tuples;

import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
public abstract class AbstractElementFinder {

	public static abstract class AbstractEnumRuleElementFinder extends AbstractElementFinder implements IEnumRuleAccess {
		@Override
		protected EObject getRootElement() {
			return getRule();
		}
	}

	public static abstract class AbstractGrammarElementFinder extends AbstractElementFinder implements IGrammarAccess {
		@Override
		protected EObject getRootElement() {
			return getGrammar();
		}

	}

	public static abstract class AbstractParserRuleElementFinder extends AbstractElementFinder implements
			IParserRuleAccess {
		@Override
		protected EObject getRootElement() {
			return getRule();
		}

	}

	public List<Assignment> findAssignments(AbstractRule... calledRules) {
		return findByNestedRuleCall(Assignment.class, calledRules);
	}

	@SuppressWarnings("unchecked")
	protected <T> List<T> findByNestedRuleCall(Class<T> clazz, AbstractRule... rule) {
		Set<AbstractRule> rls = new HashSet<AbstractRule>(Arrays.asList(rule));
		ArrayList<T> r = new ArrayList<T>();
		TreeIterator<EObject> i = getRootElement().eAllContents();
		while (i.hasNext()) {
			EObject o = i.next();
			if (clazz.isInstance(o)) {
				TreeIterator<EObject> ct = o.eAllContents();
				while (ct.hasNext()) {
					EObject cto = ct.next();
					if (cto instanceof RuleCall && rls.contains(((RuleCall) cto).getRule())) {
						r.add((T) o);
						break;
					}
				}
				i.prune();
			}
		}
		return r;
	}

	public List<CrossReference> findCrossReferences(AbstractRule... rule) {
		return findByNestedRuleCall(CrossReference.class, rule);
	}

	public List<CrossReference> findCrossReferences(EClassifier... targetEClassifiers) {
		Set<EClassifier> classifiers = new HashSet<EClassifier>(Arrays.asList(targetEClassifiers));
		Collection<EClass> classes = Collections2.forIterable(Iterables.filter(classifiers, EClass.class));
		ArrayList<CrossReference> r = new ArrayList<CrossReference>();
		TreeIterator<EObject> i = getRootElement().eAllContents();
		while (i.hasNext()) {
			EObject o = i.next();
			if (o instanceof CrossReference) {
				CrossReference c = (CrossReference) o;
				if (classifiers.contains(c.getType().getClassifier()))
					r.add(c);
				else if (c.getType().getClassifier() instanceof EClass)
					for (EClass cls : classes)
						if (cls.isSuperTypeOf((EClass) c.getType().getClassifier())) {
							r.add(c);
							break;
						}
				i.prune();
			}
		}
		return r;

	}

	public List<Pair<Keyword, Keyword>> findKeywordPairs(String leftKw, String rightKw) {
		ArrayList<Pair<Keyword, Keyword>> pairs = new ArrayList<Pair<Keyword, Keyword>>();
		Iterable<AbstractRule> rules = Iterables.emptyIterable();
		if (getRootElement() instanceof Grammar)
			rules = ((Grammar) getRootElement()).getRules();
		else if (getRootElement() instanceof ParserRule)
			rules = Arrays.asList((AbstractRule) getRootElement());
		Stack<Keyword> openings = new Stack<Keyword>();
		for (AbstractRule ar : rules)
			if (ar instanceof ParserRule && !GrammarUtil.isDatatypeRule((ParserRule) ar)) {
				TreeIterator<EObject> i = ar.eAllContents();
				while (i.hasNext()) {
					EObject o = i.next();
					if (o instanceof Keyword) {
						Keyword k = (Keyword) o;
						if (leftKw.equals(k.getValue()))
							openings.push(k);
						else if (rightKw.equals(k.getValue())) {
							if (openings.size() > 0)
								pairs.add(Tuples.create(openings.pop(), k));
						}
					}
				}
			}
		return pairs;
	}

	public List<Keyword> findKeywords(String... keywords) {
		Set<String> kwds = new HashSet<String>(Arrays.asList(keywords));
		ArrayList<Keyword> r = new ArrayList<Keyword>();
		TreeIterator<EObject> i = getRootElement().eAllContents();
		while (i.hasNext()) {
			EObject o = i.next();
			if (o instanceof Keyword) {
				Keyword k = (Keyword) o;
				if (kwds.contains(k.getValue()))
					r.add(k);
			}
		}
		return r;
	}

	public List<RuleCall> findRuleCalls(AbstractRule... rules) {
		Set<AbstractRule> rls = new HashSet<AbstractRule>(Arrays.asList(rules));
		ArrayList<RuleCall> r = new ArrayList<RuleCall>();
		TreeIterator<EObject> i = getRootElement().eAllContents();
		while (i.hasNext()) {
			EObject o = i.next();
			if (o instanceof RuleCall) {
				RuleCall c = (RuleCall) o;
				if (rls.contains(c.getRule()))
					r.add(c);
			}
		}
		return r;
	}

	// FIXME: Before uncommenting this code, it should pay attention to EClasses
	//	public Iterable<Assignment> findAssignments(EStructuralFeature feature) {
	//		return findAssignments(feature.getName());
	//	}
	//	
	//	private Iterable<Assignment> findAssignments(String feature) {
	//		ArrayList<Assignment> r = new ArrayList<Assignment>();
	//		TreeIterator<EObject> i = getRootElement().eAllContents();
	//		while (i.hasNext()) {
	//			EObject o = i.next();
	//			if (o instanceof Assignment) {
	//				Assignment a = (Assignment) o;
	//				if (feature.equals(a.getFeature()))
	//					r.add(a);
	//			}
	//		}
	//		return r;
	//	}

	protected abstract EObject getRootElement();

}
