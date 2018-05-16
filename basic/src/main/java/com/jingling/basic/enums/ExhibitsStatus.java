package com.jingling.basic.enums;

import java.util.EnumSet;

public enum  ExhibitsStatus {
    Failed("-1"),//审核未通过
    Waiting("0"),//待审核
    Passed("1")//已审核通过
    ;

    private String status;

    ExhibitsStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    /**
     * 检查输入的status是否在该枚举的范围内
     * @param status
     * @return
     */
    public static boolean checkStatus(String status) {
        EnumSet<ExhibitsStatus> enumSet = EnumSet.allOf(ExhibitsStatus.class);
        for (ExhibitsStatus exhibitsStatus : enumSet) {
            if (exhibitsStatus.getStatus().equals(status)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "ExhibitsStatus{" +
                "status='" + status + '\'' +
                '}';
    }
}
