package cn.kane.mview.manager;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cn.kane.mview.manager.definition.manager.PageDefinitionManagerTest;
import cn.kane.mview.manager.definition.manager.TemplateDefinitionManagerTest;
import cn.kane.mview.manager.definition.manager.WidgetDefinitionManagerTest;

@RunWith(Suite.class)
@SuiteClasses({
	TemplateDefinitionManagerTest.class
	,WidgetDefinitionManagerTest.class
	,PageDefinitionManagerTest.class
})
public class DefinitionManagerTestSuite {

}
