/**
 */
package uk.ac.york.cs.eng2.rcl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see uk.ac.york.cs.eng2.rcl.RclFactory
 * @model kind="package"
 * @generated
 */
public interface RclPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "rcl";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://cs.york.ac.uk/eng2/rcl";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "r";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RclPackage eINSTANCE = uk.ac.york.cs.eng2.rcl.impl.RclPackageImpl.init();

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.rcl.impl.ModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.rcl.impl.ModelImpl
	 * @see uk.ac.york.cs.eng2.rcl.impl.RclPackageImpl#getModel()
	 * @generated
	 */
	int MODEL = 0;

	/**
	 * The feature id for the '<em><b>Components</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__COMPONENTS = 0;

	/**
	 * The feature id for the '<em><b>Topics</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__TOPICS = 1;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_OPERATION_COUNT = 0;


	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.rcl.impl.ComponentImpl <em>Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.rcl.impl.ComponentImpl
	 * @see uk.ac.york.cs.eng2.rcl.impl.RclPackageImpl#getComponent()
	 * @generated
	 */
	int COMPONENT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__TRIGGERS = 1;

	/**
	 * The feature id for the '<em><b>Produces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__PRODUCES = 2;

	/**
	 * The number of structural features of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.rcl.impl.TopicImpl <em>Topic</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.rcl.impl.TopicImpl
	 * @see uk.ac.york.cs.eng2.rcl.impl.RclPackageImpl#getTopic()
	 * @generated
	 */
	int TOPIC = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC__NAME = 0;

	/**
	 * The feature id for the '<em><b>Slots</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC__SLOTS = 1;

	/**
	 * The number of structural features of the '<em>Topic</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Topic</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.rcl.impl.SlotImpl <em>Slot</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.rcl.impl.SlotImpl
	 * @see uk.ac.york.cs.eng2.rcl.impl.RclPackageImpl#getSlot()
	 * @generated
	 */
	int SLOT = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SLOT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SLOT__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Part</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SLOT__PART = 2;

	/**
	 * The number of structural features of the '<em>Slot</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SLOT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Slot</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SLOT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.rcl.impl.TriggerImpl <em>Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.rcl.impl.TriggerImpl
	 * @see uk.ac.york.cs.eng2.rcl.impl.RclPackageImpl#getTrigger()
	 * @generated
	 */
	int TRIGGER = 4;

	/**
	 * The number of structural features of the '<em>Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.rcl.impl.TimeTriggerImpl <em>Time Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.rcl.impl.TimeTriggerImpl
	 * @see uk.ac.york.cs.eng2.rcl.impl.RclPackageImpl#getTimeTrigger()
	 * @generated
	 */
	int TIME_TRIGGER = 5;

	/**
	 * The feature id for the '<em><b>Period</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_TRIGGER__PERIOD = TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Time Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_TRIGGER_FEATURE_COUNT = TRIGGER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Time Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_TRIGGER_OPERATION_COUNT = TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.rcl.impl.TopicTriggerImpl <em>Topic Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.rcl.impl.TopicTriggerImpl
	 * @see uk.ac.york.cs.eng2.rcl.impl.RclPackageImpl#getTopicTrigger()
	 * @generated
	 */
	int TOPIC_TRIGGER = 6;

	/**
	 * The feature id for the '<em><b>Topic</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TRIGGER__TOPIC = TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Topic Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TRIGGER_FEATURE_COUNT = TRIGGER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Topic Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TRIGGER_OPERATION_COUNT = TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.rcl.impl.TimePeriodImpl <em>Time Period</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.rcl.impl.TimePeriodImpl
	 * @see uk.ac.york.cs.eng2.rcl.impl.RclPackageImpl#getTimePeriod()
	 * @generated
	 */
	int TIME_PERIOD = 7;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_PERIOD__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_PERIOD__UNIT = 1;

	/**
	 * The number of structural features of the '<em>Time Period</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_PERIOD_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Time Period</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_PERIOD_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.rcl.SlotType <em>Slot Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.rcl.SlotType
	 * @see uk.ac.york.cs.eng2.rcl.impl.RclPackageImpl#getSlotType()
	 * @generated
	 */
	int SLOT_TYPE = 8;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.rcl.SlotPart <em>Slot Part</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.rcl.SlotPart
	 * @see uk.ac.york.cs.eng2.rcl.impl.RclPackageImpl#getSlotPart()
	 * @generated
	 */
	int SLOT_PART = 9;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.rcl.TimeUnit <em>Time Unit</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.rcl.TimeUnit
	 * @see uk.ac.york.cs.eng2.rcl.impl.RclPackageImpl#getTimeUnit()
	 * @generated
	 */
	int TIME_UNIT = 10;


	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.rcl.Model <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see uk.ac.york.cs.eng2.rcl.Model
	 * @generated
	 */
	EClass getModel();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.york.cs.eng2.rcl.Model#getComponents <em>Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Components</em>'.
	 * @see uk.ac.york.cs.eng2.rcl.Model#getComponents()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Components();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.york.cs.eng2.rcl.Model#getTopics <em>Topics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Topics</em>'.
	 * @see uk.ac.york.cs.eng2.rcl.Model#getTopics()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Topics();

	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.rcl.Component <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component</em>'.
	 * @see uk.ac.york.cs.eng2.rcl.Component
	 * @generated
	 */
	EClass getComponent();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.rcl.Component#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see uk.ac.york.cs.eng2.rcl.Component#getName()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.york.cs.eng2.rcl.Component#getTriggers <em>Triggers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Triggers</em>'.
	 * @see uk.ac.york.cs.eng2.rcl.Component#getTriggers()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Triggers();

	/**
	 * Returns the meta object for the reference list '{@link uk.ac.york.cs.eng2.rcl.Component#getProduces <em>Produces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Produces</em>'.
	 * @see uk.ac.york.cs.eng2.rcl.Component#getProduces()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Produces();

	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.rcl.Topic <em>Topic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Topic</em>'.
	 * @see uk.ac.york.cs.eng2.rcl.Topic
	 * @generated
	 */
	EClass getTopic();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.rcl.Topic#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see uk.ac.york.cs.eng2.rcl.Topic#getName()
	 * @see #getTopic()
	 * @generated
	 */
	EAttribute getTopic_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.york.cs.eng2.rcl.Topic#getSlots <em>Slots</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Slots</em>'.
	 * @see uk.ac.york.cs.eng2.rcl.Topic#getSlots()
	 * @see #getTopic()
	 * @generated
	 */
	EReference getTopic_Slots();

	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.rcl.Slot <em>Slot</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Slot</em>'.
	 * @see uk.ac.york.cs.eng2.rcl.Slot
	 * @generated
	 */
	EClass getSlot();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.rcl.Slot#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see uk.ac.york.cs.eng2.rcl.Slot#getName()
	 * @see #getSlot()
	 * @generated
	 */
	EAttribute getSlot_Name();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.rcl.Slot#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see uk.ac.york.cs.eng2.rcl.Slot#getType()
	 * @see #getSlot()
	 * @generated
	 */
	EAttribute getSlot_Type();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.rcl.Slot#getPart <em>Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Part</em>'.
	 * @see uk.ac.york.cs.eng2.rcl.Slot#getPart()
	 * @see #getSlot()
	 * @generated
	 */
	EAttribute getSlot_Part();

	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.rcl.Trigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trigger</em>'.
	 * @see uk.ac.york.cs.eng2.rcl.Trigger
	 * @generated
	 */
	EClass getTrigger();

	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.rcl.TimeTrigger <em>Time Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Time Trigger</em>'.
	 * @see uk.ac.york.cs.eng2.rcl.TimeTrigger
	 * @generated
	 */
	EClass getTimeTrigger();

	/**
	 * Returns the meta object for the containment reference '{@link uk.ac.york.cs.eng2.rcl.TimeTrigger#getPeriod <em>Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Period</em>'.
	 * @see uk.ac.york.cs.eng2.rcl.TimeTrigger#getPeriod()
	 * @see #getTimeTrigger()
	 * @generated
	 */
	EReference getTimeTrigger_Period();

	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.rcl.TopicTrigger <em>Topic Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Topic Trigger</em>'.
	 * @see uk.ac.york.cs.eng2.rcl.TopicTrigger
	 * @generated
	 */
	EClass getTopicTrigger();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.york.cs.eng2.rcl.TopicTrigger#getTopic <em>Topic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Topic</em>'.
	 * @see uk.ac.york.cs.eng2.rcl.TopicTrigger#getTopic()
	 * @see #getTopicTrigger()
	 * @generated
	 */
	EReference getTopicTrigger_Topic();

	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.rcl.TimePeriod <em>Time Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Time Period</em>'.
	 * @see uk.ac.york.cs.eng2.rcl.TimePeriod
	 * @generated
	 */
	EClass getTimePeriod();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.rcl.TimePeriod#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see uk.ac.york.cs.eng2.rcl.TimePeriod#getValue()
	 * @see #getTimePeriod()
	 * @generated
	 */
	EAttribute getTimePeriod_Value();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.rcl.TimePeriod#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit</em>'.
	 * @see uk.ac.york.cs.eng2.rcl.TimePeriod#getUnit()
	 * @see #getTimePeriod()
	 * @generated
	 */
	EAttribute getTimePeriod_Unit();

	/**
	 * Returns the meta object for enum '{@link uk.ac.york.cs.eng2.rcl.SlotType <em>Slot Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Slot Type</em>'.
	 * @see uk.ac.york.cs.eng2.rcl.SlotType
	 * @generated
	 */
	EEnum getSlotType();

	/**
	 * Returns the meta object for enum '{@link uk.ac.york.cs.eng2.rcl.SlotPart <em>Slot Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Slot Part</em>'.
	 * @see uk.ac.york.cs.eng2.rcl.SlotPart
	 * @generated
	 */
	EEnum getSlotPart();

	/**
	 * Returns the meta object for enum '{@link uk.ac.york.cs.eng2.rcl.TimeUnit <em>Time Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Time Unit</em>'.
	 * @see uk.ac.york.cs.eng2.rcl.TimeUnit
	 * @generated
	 */
	EEnum getTimeUnit();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	RclFactory getRclFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.rcl.impl.ModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.rcl.impl.ModelImpl
		 * @see uk.ac.york.cs.eng2.rcl.impl.RclPackageImpl#getModel()
		 * @generated
		 */
		EClass MODEL = eINSTANCE.getModel();
		/**
		 * The meta object literal for the '<em><b>Components</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__COMPONENTS = eINSTANCE.getModel_Components();
		/**
		 * The meta object literal for the '<em><b>Topics</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__TOPICS = eINSTANCE.getModel_Topics();
		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.rcl.impl.ComponentImpl <em>Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.rcl.impl.ComponentImpl
		 * @see uk.ac.york.cs.eng2.rcl.impl.RclPackageImpl#getComponent()
		 * @generated
		 */
		EClass COMPONENT = eINSTANCE.getComponent();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__NAME = eINSTANCE.getComponent_Name();
		/**
		 * The meta object literal for the '<em><b>Triggers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__TRIGGERS = eINSTANCE.getComponent_Triggers();
		/**
		 * The meta object literal for the '<em><b>Produces</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__PRODUCES = eINSTANCE.getComponent_Produces();
		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.rcl.impl.TopicImpl <em>Topic</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.rcl.impl.TopicImpl
		 * @see uk.ac.york.cs.eng2.rcl.impl.RclPackageImpl#getTopic()
		 * @generated
		 */
		EClass TOPIC = eINSTANCE.getTopic();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOPIC__NAME = eINSTANCE.getTopic_Name();
		/**
		 * The meta object literal for the '<em><b>Slots</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOPIC__SLOTS = eINSTANCE.getTopic_Slots();
		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.rcl.impl.SlotImpl <em>Slot</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.rcl.impl.SlotImpl
		 * @see uk.ac.york.cs.eng2.rcl.impl.RclPackageImpl#getSlot()
		 * @generated
		 */
		EClass SLOT = eINSTANCE.getSlot();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SLOT__NAME = eINSTANCE.getSlot_Name();
		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SLOT__TYPE = eINSTANCE.getSlot_Type();
		/**
		 * The meta object literal for the '<em><b>Part</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SLOT__PART = eINSTANCE.getSlot_Part();
		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.rcl.impl.TriggerImpl <em>Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.rcl.impl.TriggerImpl
		 * @see uk.ac.york.cs.eng2.rcl.impl.RclPackageImpl#getTrigger()
		 * @generated
		 */
		EClass TRIGGER = eINSTANCE.getTrigger();
		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.rcl.impl.TimeTriggerImpl <em>Time Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.rcl.impl.TimeTriggerImpl
		 * @see uk.ac.york.cs.eng2.rcl.impl.RclPackageImpl#getTimeTrigger()
		 * @generated
		 */
		EClass TIME_TRIGGER = eINSTANCE.getTimeTrigger();
		/**
		 * The meta object literal for the '<em><b>Period</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIME_TRIGGER__PERIOD = eINSTANCE.getTimeTrigger_Period();
		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.rcl.impl.TopicTriggerImpl <em>Topic Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.rcl.impl.TopicTriggerImpl
		 * @see uk.ac.york.cs.eng2.rcl.impl.RclPackageImpl#getTopicTrigger()
		 * @generated
		 */
		EClass TOPIC_TRIGGER = eINSTANCE.getTopicTrigger();
		/**
		 * The meta object literal for the '<em><b>Topic</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOPIC_TRIGGER__TOPIC = eINSTANCE.getTopicTrigger_Topic();
		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.rcl.impl.TimePeriodImpl <em>Time Period</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.rcl.impl.TimePeriodImpl
		 * @see uk.ac.york.cs.eng2.rcl.impl.RclPackageImpl#getTimePeriod()
		 * @generated
		 */
		EClass TIME_PERIOD = eINSTANCE.getTimePeriod();
		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIME_PERIOD__VALUE = eINSTANCE.getTimePeriod_Value();
		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIME_PERIOD__UNIT = eINSTANCE.getTimePeriod_Unit();
		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.rcl.SlotType <em>Slot Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.rcl.SlotType
		 * @see uk.ac.york.cs.eng2.rcl.impl.RclPackageImpl#getSlotType()
		 * @generated
		 */
		EEnum SLOT_TYPE = eINSTANCE.getSlotType();
		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.rcl.SlotPart <em>Slot Part</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.rcl.SlotPart
		 * @see uk.ac.york.cs.eng2.rcl.impl.RclPackageImpl#getSlotPart()
		 * @generated
		 */
		EEnum SLOT_PART = eINSTANCE.getSlotPart();
		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.rcl.TimeUnit <em>Time Unit</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.rcl.TimeUnit
		 * @see uk.ac.york.cs.eng2.rcl.impl.RclPackageImpl#getTimeUnit()
		 * @generated
		 */
		EEnum TIME_UNIT = eINSTANCE.getTimeUnit();

	}

} //RclPackage
