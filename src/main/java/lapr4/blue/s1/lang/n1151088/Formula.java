/*
 * Copyright (c) 2005 Einar Pehrson <einar@pehrson.nu>.
 *
 * This file is part of
 * CleanSheets - a spreadsheet application for the Java platform.
 *
 * CleanSheets is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * CleanSheets is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CleanSheets; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package lapr4.blue.s1.lang.n1151088;

import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.core.formula.Reference;
import csheets.core.formula.util.ExpressionVisitor;
import csheets.core.formula.util.ExpressionVisitorException;
import csheets.core.formula.util.ReferenceFetcher;
import java.util.SortedSet;
import java.util.TreeSet;
import lapr4.blue.s1.lang.n1151088.temporaryVariables.TemporaryVarContentor;
import lapr4.blue.s1.lang.n1151088.temporaryVariables.TemporaryVariable;

/**
 * A formula in a cell.
 * @author Diana Silva on 03/06/2017
 * @author Einar Pehrson
 */
public class Formula implements Expression {

	/** The unique version identifier used for serialization */
	private static final long serialVersionUID = 7127589370042533160L;

	/** The cell to which the formula belongs */
	private Cell cell;

	/** The expression of the formula */
	private Expression expression;

	/** Returns the references in the expression */
	private SortedSet<Reference> references;
        
        /** The temporary variables in the expression */
        private TemporaryVarContentor contentor;


	/**
	 * Creates a new formula.
	 * @param cell the cell to which the formula belongs
	 * @param expression the expression in the formula
	 */
	public Formula(Cell cell, Expression expression) {
		// Stores members
		this.cell = cell;
		this.expression = expression;
	}

        @Override
	public Value evaluate() throws IllegalValueTypeException {
		if (!hasCircularReference())
			return expression.evaluate();
		else
			return new Value(new CircularReferenceException(this));
	}

        @Override
	public Object accept(ExpressionVisitor visitor) {
		return expression.accept(visitor);
	}

	/**
	 * Returns the cell to which the formula belongs.
	 * @return the cell to which the formula belongs
	 */
	public Cell getCell() {
		return cell;
	}

	/**
	 * Returns the expression in the formula.
	 * @return the expression in the formula
	 */
	public Expression getExpression() {
		return expression;
	}

	/**
	 * Returns the references in the expression.
	 * @return the references in the expression
	 */
	public SortedSet<Reference> getReferences() {
		if (references == null)
			references = new ReferenceFetcher().getReferences(expression);
		return new TreeSet<Reference>(references);
	}
        
        /**
         * Returns the temporary variables contentor
         * @return the temporary variables in the expression
         */
         public TemporaryVarContentor getTemporaryVarContentor(){
            if(contentor==null)
                contentor=new TemporaryVarContentor();
            return contentor;
        }

	/**
	 * Checks if the given formula has any circular references.
         * @return return  
	 * @throws CircularReferenceException if the formula contains any circular references
	 */
	public boolean hasCircularReference() {
		try {
			new CircularReferenceFinder().check(this);
		} catch (ExpressionVisitorException e) {
			return true;
		}
		return false;
	}

	/**
	 * Returns a string representation of the formula.
	 * @return a string representation of the formula
	 */
        @Override
	public String toString() {
		return expression.toString();
	}
}