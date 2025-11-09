package com.cdpjenkins.genovianpearconsultation;

import com.cdpjenkins.genovianpearconsultation.core.ConsultationFormService;
import com.cdpjenkins.genovianpearconsultation.resources.ConsultationFormResource;
import com.cdpjenkins.genovianpearconsultation.resources.ConsultationFormExceptionMapper;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

public class GenovianPearConsultationApplication extends Application<GenovianPearConsultationConfiguration> {

    public static void main(final String[] args) throws Exception {
        new GenovianPearConsultationApplication().run(args);
    }

    @Override
    public String getName() {
        return "GenovianPearConsultation";
    }

    @Override
    public void initialize(final Bootstrap<GenovianPearConsultationConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final GenovianPearConsultationConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new ConsultationFormExceptionMapper());

        environment.jersey().register(new ConsultationFormResource(new ConsultationFormService()));
    }
}
