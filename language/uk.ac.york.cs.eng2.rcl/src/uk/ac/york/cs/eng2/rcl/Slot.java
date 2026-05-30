/**
 */
package uk.ac.york.cs.eng2.rcl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Slot</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.york.cs.eng2.rcl.Slot#getName <em>Name</em>}</li>
 *   <li>{@link uk.ac.york.cs.eng2.rcl.Slot#getType <em>Type</em>}</li>
 *   <li>{@link uk.ac.york.cs.eng2.rcl.Slot#getPart <em>Part</em>}</li>
 * </ul>
 *
 * @see uk.ac.york.cs.eng2.rcl.RclPackage#getSlot()
 * @model
 * @generated
 */
public interface Slot extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see uk.ac.york.cs.eng2.rcl.RclPackage#getSlot_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link uk.ac.york.cs.eng2.rcl.Slot#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link uk.ac.york.cs.eng2.rcl.SlotType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see uk.ac.york.cs.eng2.rcl.SlotType
	 * @see #setType(SlotType)
	 * @see uk.ac.york.cs.eng2.rcl.RclPackage#getSlot_Type()
	 * @model
	 * @generated
	 */
	SlotType getType();

	/**
	 * Sets the value of the '{@link uk.ac.york.cs.eng2.rcl.Slot#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see uk.ac.york.cs.eng2.rcl.SlotType
	 * @see #getType()
	 * @generated
	 */
	void setType(SlotType value);

	/**
	 * Returns the value of the '<em><b>Part</b></em>' attribute.
	 * The default value is <code>"body"</code>.
	 * The literals are from the enumeration {@link uk.ac.york.cs.eng2.rcl.SlotPart}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Part</em>' attribute.
	 * @see uk.ac.york.cs.eng2.rcl.SlotPart
	 * @see #setPart(SlotPart)
	 * @see uk.ac.york.cs.eng2.rcl.RclPackage#getSlot_Part()
	 * @model default="body"
	 * @generated
	 */
	SlotPart getPart();

	/**
	 * Sets the value of the '{@link uk.ac.york.cs.eng2.rcl.Slot#getPart <em>Part</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Part</em>' attribute.
	 * @see uk.ac.york.cs.eng2.rcl.SlotPart
	 * @see #getPart()
	 * @generated
	 */
	void setPart(SlotPart value);

} // Slot
