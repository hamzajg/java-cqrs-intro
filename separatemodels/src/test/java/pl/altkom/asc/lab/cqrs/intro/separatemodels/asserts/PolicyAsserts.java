package pl.altkom.asc.lab.cqrs.intro.separatemodels.asserts;

import org.junit.jupiter.api.Assertions;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Policy;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.PolicyVersion;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.primitives.MonetaryAmount;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel.PolicyInfoDto;

public class PolicyAsserts {
    public static void assertPolicyWithPolicyInfoDto(Policy expected, PolicyInfoDto actual) {
        Assertions.assertNotNull(actual);
        PolicyVersion policyVersion = expected.getPolicyVersions().withNumber(1).get();
        Assertions.assertEquals(policyVersion.getCoverPeriod().getFrom(), actual.getCoverFrom());
        Assertions.assertEquals(policyVersion.getCoverPeriod().getTo(), actual.getCoverTo());
        Assertions.assertEquals(policyVersion.getPolicyHolder().getFullName(), actual.getPolicyHolder());
        Assertions.assertEquals(policyVersion.getCar().getPlaceNumberWithMake(), actual.getVehicle());
        Assertions.assertEquals(policyVersion.getTotalPremium(), MonetaryAmount.from(actual.getTotalPremiumAmount()));
    }
}
