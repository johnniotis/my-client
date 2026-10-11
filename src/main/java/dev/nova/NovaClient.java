package com.example.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class CustomClientMod implements ClientModInitializer {

    // Feature toggles
    public static boolean autoAttackEnabled = false;
    public static boolean espEnabled = true;

    // Keybindings
    private static KeyBinding menuKeyBind;

    @Override
    public void onInitializeClient() {
        // Register keybind for opening the GUI menu (Default: Right Shift)
        menuKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.customclient.menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.customclient.main"
        ));

        // Register client tick listener
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            // Check if the menu key was pressed
            while (menuKeyBind.wasPressed()) {
                // Code to open your GUI Screen goes here
                // client.setScreen(new CustomClientScreen());
            }

            // Example Auto Attack logic hook
            if (autoAttackEnabled) {
                if (client.targetedEntity != null && client.player.getAttackCooldownProgress(0.5f) >= 1.0f) {
                    client.interactionManager.attackEntity(client.player, client.targetedEntity);
                    client.player.swingHand(net.minecraft.util.Hand.MAIN_HAND);
                }
            }
        });
    }
}
