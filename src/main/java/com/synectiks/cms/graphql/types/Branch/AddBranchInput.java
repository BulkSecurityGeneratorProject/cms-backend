package com.synectiks.cms.graphql.types.Branch;

public class AddBranchInput extends AbstractBranchInput {
    private Long collegeId;
    private Long cityId;
    private Long stateId;

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    @Override
    public String toString() {
        return "AddBranchInput{" +
            "collegeId=" + collegeId +
            ", cityId=" + cityId +
            ", stateId=" + stateId +
            '}';
    }
}
