package io.github.compose4gtk.modifier

import org.gnome.gdk.ModifierType
import org.gnome.gtk.EventControllerKey
import org.gnome.gtk.PropagationPhase

fun Modifier.keyPressed(
    phase: PropagationPhase = PropagationPhase.CAPTURE,
    action: (keyval: Int, keycode: Int, state: Set<ModifierType>) -> Boolean
): Modifier {
    val gesture = EventControllerKey.builder()
        .setPropagationPhase(phase)
        .onKeyPressed(action)
        .build()

    return combine(
        apply = {
            it.addController(gesture)
        },
        undo = {
            it.removeController(gesture)
        },
    )
}

fun Modifier.keyReleased(
    phase: PropagationPhase = PropagationPhase.CAPTURE,
    action: (keyval: Int, keycode: Int, state: Set<ModifierType>) -> Unit
): Modifier {
    val gesture = EventControllerKey.builder()
        .setPropagationPhase(phase)
        .onKeyReleased(action)
        .build()

    return combine(
        apply = {
            it.addController(gesture)
        },
        undo = {
            it.removeController(gesture)
        },
    )
}