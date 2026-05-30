/**
 */
package uk.ac.york.cs.eng2.rcl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Time Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.york.cs.eng2.rcl.TimeTrigger#getPeriod <em>Period</em>}</li>
 * </ul>
 *
 * @see uk.ac.york.cs.eng2.rcl.RclPackage#getTimeTrigger()
 * @model
 * @generated
 */
public interface TimeTrigger extends Trigger {
	/**
	 * Returns the value of the '<em><b>Period</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Period</em>' containment reference.
	 * @see #setPeriod(TimePeriod)
	 * @see uk.ac.york.cs.eng2.rcl.RclPackage#getTimeTrigger_Period()
	 * @model containment="true" required="true"
	 * @generated
	 */
	TimePeriod getPeriod();

	/**
	 * Sets the value of the '{@link uk.ac.york.cs.eng2.rcl.TimeTrigger#getPeriod <em>Period</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Period</em>' containment reference.
	 * @see #getPeriod()
	 * @generated
	 */
	void setPeriod(TimePeriod value);

} // TimeTrigger
