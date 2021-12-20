package ca.uhn.fhir.jpa.starter;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Subscription;

@Interceptor
public class CustomLoggingInterceptor
{
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CustomLoggingInterceptor.class);

	@Hook(Pointcut.STORAGE_PRESTORAGE_RESOURCE_CREATED)
	public void insert(IBaseResource theResource) {
		if (theResource instanceof Patient) {
			logger.info("New Patient resource created with ID: {}.", ((Patient) theResource).getId());
		}
		else if (theResource instanceof Subscription) {
			logger.info("New subscription added with ID: {}.", ((Subscription) theResource).getId());
		}
		else {
			logger.info("New Resource added.");
		}
	}
}
