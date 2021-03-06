package unis.editor;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import unis.modle.ExoticType;

public final class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {

	public void registerCustomEditors(PropertyEditorRegistry registry) {

		// it is expected that new PropertyEditor instances are created
		registry.registerCustomEditor(ExoticType.class, new ExoticTypeEditor());

		// you could register as many custom property editors as are required
		// here...
	}
}