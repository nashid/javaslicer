package de.unisb.cs.st.javaslicer.traceResult;

import de.unisb.cs.st.javaslicer.common.classRepresentation.InstructionInstance;
import de.unisb.cs.st.javaslicer.common.classRepresentation.InstructionType;
import de.unisb.cs.st.javaslicer.common.classRepresentation.instructions.LabelMarker;


/**
 * This interface defines filters that are used in the traversal of an execution
 * trace to filter out certain instances.
 *
 * The default implementations are {@link LabelFilter} and {@link AdditionalLabelFilter}.
 *
 * @author Clemens Hammacher
 */
public interface InstanceFilter<InstanceType> {

    public static class LabelFilter implements InstanceFilter<InstructionInstance> {

        public static LabelFilter instance = new LabelFilter();

        public boolean filterInstance(final InstructionInstance instrInstance) {
            return instrInstance.getInstruction().getType() == InstructionType.LABEL;
        }

        private LabelFilter() {
            // singleton!
        }

    }

    public static class AdditionalLabelFilter implements InstanceFilter<InstructionInstance> {

        public static AdditionalLabelFilter instance = new AdditionalLabelFilter();

        public boolean filterInstance(final InstructionInstance instrInstance) {
            return (instrInstance.getInstruction().getType() == InstructionType.LABEL) &&
                (((LabelMarker)instrInstance.getInstruction()).isAdditionalLabel());
        }

        private AdditionalLabelFilter() {
            // singleton!
        }

    }

    /**
     * Returns true if the instance should be filtered out
     */
    boolean filterInstance(InstanceType instance);

}
