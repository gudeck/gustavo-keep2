package br.com.basis.keep2.batch;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("br.com.basis.colatina.gcz.keep2");

        noClasses()
            .that()
            .resideInAnyPackage("br.com.basis.colatina.gcz.keep2.service..")
            .or()
            .resideInAnyPackage("br.com.basis.colatina.gcz.keep2.repository..")
            .should().dependOnClassesThat()
            .resideInAnyPackage("..br.com.basis.colatina.gcz.keep2.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
