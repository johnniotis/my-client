import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class CustomClientMod implements ClientModInitializer {
    public static boolean espEnabled = true;
    public static boolean autoAttackEnabled = false;
    private static KeyBinding menuKeyBind;

    @Override
    public void onInitializeClient() {
        // Register a keybind to open the GUI menu (e.g., Right Shift)
        menuKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.customclient.menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.customclient.main"
        ));

        // Handle tick events for modules
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            // Check if menu key is pressed
            while (menuKeyBind.wasPressed()) {
                // Open your custom GUI Screen here
                // client.setScreen(new CustomMenuScreen());
            }

            // Run Auto Attack logic if toggled
            if (autoAttackEnabled) {
                // Implement attack tick check
            }
        });
    }
}
