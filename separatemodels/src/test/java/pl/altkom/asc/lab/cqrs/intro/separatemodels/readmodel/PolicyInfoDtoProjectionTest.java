package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.SeparateModelsApplication;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.asserts.PolicyAsserts;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Policy;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.testdatabuilders.PoliciesTestDataBuilder;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SeparateModelsApplication.class)
public class PolicyInfoDtoProjectionTest {

    @Autowired
    PolicyInfoDtoRepository repository;

    @Test
    public void testCreatePolicyInfoDto() {
        //given
        Policy policy = PoliciesTestDataBuilder.standardOneYearPolicy(LocalDate.of(2019, 1, 1));
        PolicyInfoDtoProjection projection = new PolicyInfoDtoProjection(repository);

        //when
        projection.createPolicyInfoDto(policy);

        //then
        PolicyInfoDto byPolicyId = repository.findByPolicyId(policy.getId()).orElse(null);
        PolicyAsserts.assertPolicyWithPolicyInfoDto(policy, byPolicyId);
    }
}
