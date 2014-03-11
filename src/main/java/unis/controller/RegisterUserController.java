package unis.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

public final class RegisterUserController extends SimpleFormController{

	private final PropertyEditorRegistrar customPropertyEditorRegistrar;

	public RegisterUserController(PropertyEditorRegistrar propertyEditorRegistrar) {
		this.customPropertyEditorRegistrar = propertyEditorRegistrar;
	}

	protected void initBinder(HttpServletRequest request,ServletRequestDataBinder binder) throws Exception {
		this.customPropertyEditorRegistrar.registerCustomEditors(binder);
	}

	// other methods to do with registering a User
}