package net.bytebuddy.modifier;

import net.bytebuddy.instrumentation.ModifierContributor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.objectweb.asm.Opcodes;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class SynchronizationStateTest extends AbstractModifierContributorTest {

    public SynchronizationStateTest(ModifierContributor modifierContributor, int expectedModifier) {
        super(modifierContributor, expectedModifier);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                { SynchronizationState.is(false), 0 },
                { SynchronizationState.PLAIN, 0 },
                { SynchronizationState.is(true), Opcodes.ACC_SYNCHRONIZED },
                { SynchronizationState.SYNCHRONIZED, Opcodes.ACC_SYNCHRONIZED }
        });
    }

    @Test
    public void testState() throws Exception {
        assertThat(((SynchronizationState) modifierContributor).isSynchronized(), is(expectedModifier != 0));
    }
}
