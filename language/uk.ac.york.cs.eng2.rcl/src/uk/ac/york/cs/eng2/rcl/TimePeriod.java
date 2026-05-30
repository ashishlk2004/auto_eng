/**
 */
package uk.ac.york.cs.eng2.rcl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Time Period</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.york.cs.eng2.rcl.TimePeriod#getValue <em>Value</em>}</li>
 *   <li>{@link uk.ac.york.cs.eng2.rcl.TimePeriod#getUnit <em>Unit</em>}</li>
 * </ul>
 *
 * @see uk.ac.york.cs.eng2.rcl.RclPackage#getTimePeriod()
 * @model
 * @generated
 */
public interface TimePeriod extends EObject {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(int)
	 * @see uk.ac.york.cs.eng2.rcl.RclPackage#getTimePeriod_Value()
	 * @model default="1"
	 * @generated
	 */
	int getValue();

	/**
	 * Sets the value of the '{@link uk.ac.york.cs.eng2.rcl.TimePeriod#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(int value);

	/**
	 * Returns the value of the '<em><b>Unit</b></em>' attribute.
	 * The literals are from the enumeration {@link uk.ac.york.cs.eng2.rcl.TimeUnit}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unit</em>' attribute.
	 * @see uk.ac.york.cs.eng2.rcl.TimeUnit
	 * @see #setUnit(TimeUnit)
	 * @see uk.ac.york.cs.eng2.rcl.RclPackage#getTimePeriod_Unit()
	 * @model
	 * @generated
	 */
	TimeUnit getUnit();

	/**
	 * Sets the value of the '{@link uk.ac.york.cs.eng2.rcl.TimePeriod#getUnit <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unit</em>' attribute.
	 * @see uk.ac.york.cs.eng2.rcl.TimeUnit
	 * @see #getUnit()
	 * @generated
	 */
	void setUnit(TimeUnit value);

} // TimePeriod
