/**
 */
package uk.ac.york.cs.eng2.rcl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Topic Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.york.cs.eng2.rcl.TopicTrigger#getTopic <em>Topic</em>}</li>
 * </ul>
 *
 * @see uk.ac.york.cs.eng2.rcl.RclPackage#getTopicTrigger()
 * @model
 * @generated
 */
public interface TopicTrigger extends Trigger {
	/**
	 * Returns the value of the '<em><b>Topic</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Topic</em>' reference.
	 * @see #setTopic(Topic)
	 * @see uk.ac.york.cs.eng2.rcl.RclPackage#getTopicTrigger_Topic()
	 * @model required="true"
	 * @generated
	 */
	Topic getTopic();

	/**
	 * Sets the value of the '{@link uk.ac.york.cs.eng2.rcl.TopicTrigger#getTopic <em>Topic</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Topic</em>' reference.
	 * @see #getTopic()
	 * @generated
	 */
	void setTopic(Topic value);

} // TopicTrigger
