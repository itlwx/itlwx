package com.itlwx.common.valid;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, GroupGeneral.class})
public interface GroupAll {

}
