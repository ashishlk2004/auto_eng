package uk.ac.york.cs.eng2.rcl.viewpoint;

import org.eclipse.emf.ecore.EObject;

import uk.ac.york.cs.eng2.rcl.Component;
import uk.ac.york.cs.eng2.rcl.Model;
import uk.ac.york.cs.eng2.rcl.Topic;
import uk.ac.york.cs.eng2.rcl.TopicTrigger;
import uk.ac.york.cs.eng2.rcl.Trigger;

/**
 * Sirius services for the Reactive Component Language viewpoint. The expressions
 * here back custom AQL/operations referenced from {@code viewpoint.odesign}.
 */
public class Services {

    /** Returns the {@link Model} root for any element in the tree. */
    public Model rootModel(EObject self) {
        EObject current = self;
        while (current != null && !(current instanceof Model)) {
            current = current.eContainer();
        }
        return (Model) current;
    }

    /**
     * Builds a multi-line summary label for a {@link Component}, including the
     * names of its triggers. Sirius can call this with {@code service:label}.
     */
    public String label(Component component) {
        StringBuilder sb = new StringBuilder(component.getName());
        if (!component.getTriggers().isEmpty()) {
            sb.append('\n');
            boolean first = true;
            for (Trigger t : component.getTriggers()) {
                if (!first) {
                    sb.append(", ");
                }
                if (t instanceof TopicTrigger) {
                    Topic tp = ((TopicTrigger) t).getTopic();
                    sb.append('<').append(tp != null ? tp.getName() : "?").append('>');
                } else {
                    sb.append("[time]");
                }
                first = false;
            }
        }
        return sb.toString();
    }

    /** Number of slots a topic has, used in tool-tips. */
    public int slotCount(Topic topic) {
        return topic.getSlots() == null ? 0 : topic.getSlots().size();
    }
}
