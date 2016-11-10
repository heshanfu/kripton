package kripton70;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class Kripton70Test {

	@Test
	public void test() throws IOException
	{
		Bean bean=new Bean();
		bean.id=25;
		bean.description="hello";
		String output=KriptonLibrary2.serialize(bean);
		System.out.println(output);
		
		Bean bean2=KriptonLibrary2.parse(output, Bean.class);
		
		Assert.assertTrue(bean2.equals(bean));
		
		System.out.println(output);
		
	}
}
