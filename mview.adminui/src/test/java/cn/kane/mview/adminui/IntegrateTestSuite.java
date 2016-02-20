package cn.kane.mview.adminui;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cn.kane.mview.adminui.integrade.ChangesManageServiceTest;
import cn.kane.mview.adminui.integrade.ChangesPublishServiceTest;
import cn.kane.mview.adminui.integrade.RequirementManageServiceTest;
import cn.kane.mview.adminui.integrade.RequirementMemberServiceTest;
import cn.kane.mview.adminui.integrade.VersionControlInterceptorTest;

@RunWith(Suite.class)
@SuiteClasses({
	RequirementManageServiceTest.class
	,RequirementMemberServiceTest.class
	,ChangesManageServiceTest.class
	,VersionControlInterceptorTest.class
	,ChangesPublishServiceTest.class
})
public class IntegrateTestSuite {

}
