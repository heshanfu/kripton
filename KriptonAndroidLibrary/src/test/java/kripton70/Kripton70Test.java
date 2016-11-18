package kripton70;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import org.codehaus.stax2.XMLOutputFactory2;
import org.codehaus.stax2.XMLStreamWriter2;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.abubusoft.kripton.binder2.context.PropertiesBinderContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.context.YamlBinderContext;
import com.abubusoft.kripton.binder2.core.BinderType;
import com.abubusoft.kripton.binder2.core.KriptonLibrary2;

public class Kripton70Test {
	
	@Before
	public void setup()
	{
		KriptonLibrary2.registryBinder(new YamlBinderContext());
		KriptonLibrary2.registryBinder(new PropertiesBinderContext());
		KriptonLibrary2.registryBinder(new XmlBinderContext());
	}

	@Test
	public void test() throws IOException
	{
		//http://www.studytrails.com/java/xml/woodstox/java-xml-stax-woodstox-basic-parsing/
		Bean bean=new Bean();
		bean.id=25;
		bean.name="\"hello===ciao\"";
		bean.valueString="<hello===ciao]]>";
		bean.content="<hello===ciao]]>";
		
		bean.valueByteType=12;
		bean.valueCharType='a';
		bean.valueShortType=13;
		bean.valueIntType=13;
		bean.valueLongType=13;
		bean.valueFloatType=13;
		bean.valueDoubleType=13;
		
		bean.valueByte=12;
		bean.valueChar='a';
		bean.valueShort=13;
		bean.valueInt=13;
		bean.valueLong=13L;
		bean.valueFloat=13.0f;
		bean.valueDouble=13.0;
		
		bean.valueBean=new Bean();
		bean.valueBean.id=25;
		bean.valueBean.valueString="hello";
		
		/*bean.valueStringList=new ArrayList<>();
		bean.valueStringList.add("hello1");
		bean.valueStringList.add("hello2");
		bean.valueStringList.add("hello3");
		
		bean.valueStringArray=new String[2];
		bean.valueStringArray[0]="arrayString0";
		bean.valueStringArray[1]="arrayString1";
		
		bean.valueStringMap=new HashMap<>();
		bean.valueStringMap.put("aa", "bb");
		bean.valueStringMap.put("cc", "dd");
		
		*/
		
		BinderType type = BinderType.XML;
		
		String output=KriptonLibrary2.getBinder(type).serialize(bean);
		System.out.println(output);
		
		Bean bean2=KriptonLibrary2.getBinder(type).parse(output, Bean.class);
		
		Assert.assertTrue(bean2.equals(bean));
		
		System.out.println(output);
		
	}
	
	@Test
	public void testXml() throws XMLStreamException
	{
		XMLOutputFactory2 xmlOutputFactory = (XMLOutputFactory2) XMLOutputFactory.newFactory();
        
        XMLStreamWriter2 serializer = (XMLStreamWriter2) xmlOutputFactory.createXMLStreamWriter(System.out);
         
        serializer.writeStartDocument();
         
        serializer.writeStartElement("employee");
        	serializer.writeAttribute("loca", String.valueOf(12));        	
            serializer.writeStartElement("id");
                serializer.writeCharacters("1");
            serializer.writeEndElement();
             
            serializer.writeStartElement("name");
                serializer.writeCharacters("Alba");
            serializer.writeEndElement();
             
            serializer.writeStartElement("salary");
                serializer.writeCharacters("100");
            serializer.writeEndElement();
            //escaping special character '&'
            serializer.writeStartElement("address");
                serializer.writeCharacters("Beth & Cathy Rd, Dessert Street, Elephant County");
            serializer.writeEndElement();
        serializer.writeEndElement();
        
        serializer.writeEndDocument();
        serializer.flush();
	}
}
