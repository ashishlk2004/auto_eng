/**
 */
package uk.ac.york.cs.eng2.rcl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.york.cs.eng2.rcl.Model#getComponents <em>Components</em>}</li>
 *   <li>{@link uk.ac.york.cs.eng2.rcl.Model#getTopics <em>Topics</em>}</li>
 * </ul>
 *
 * @see uk.ac.york.cs.eng2.rcl.RclPackage#getModel()
 * @model
 * @generated
 */
public interface Model extends EObject {

	/**
	 * Returns the value of the '<em><b>Components</b></em>' containment reference list.
	 * The list contents are of type {@link uk.ac.york.cs.eng2.rcl.Component}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Components</em>' containment reference list.
	 * @see uk.ac.york.cs.eng2.rcl.RclPackage#getModel_Components()
	 * @model containment="true"
	 * @generated
	 */
	EList<Component> getComponents();

	/**
	 * Returns the value of the '<em><b>Topics</b></em>' containment reference list.
	 * The list contents are of type {@link uk.ac.york.cs.eng2.rcl.Topic}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Topics</em>' containment reference list.
	 * @see uk.ac.york.cs.eng2.rcl.RclPackage#getModel_Topics()
	 * @model containment="true"
	 * @generated
	 */
	EList<Topic> getTopics();
} // Model
