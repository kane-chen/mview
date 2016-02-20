package cn.kane.mview.adminui;

import cn.kane.mview.service.definition.entity.DefinitionKey;

import com.alibaba.fastjson.JSON;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        DefinitionKey key = new DefinitionKey() ;
		key.setName("kane");
		key.setType("css");
		key.setVersion("1");
		System.out.println(JSON.toJSONString(key));
    }
}
