/**
 * 
 */
package issue.kripton_1;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import com.abubusoft.kripton.BinderReader;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.android.BinderFactory;
import com.abubusoft.kripton.android.BinderFactory.ReaderType;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.exception.WriterException;

/**
 * @author xcesco
 *
 */
public class Work05Test {

	private Bean05 bean;

	@Before
	public void setup()
	{
		bean=new Bean05();
		
		bean.setName("Tonj");
		bean.setSurname("Manero");
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		bean.setBirthday(calendar.getTime());
		Integer[] array= {1, 2, 4};
		
		bean.setTickets(array);
	}
	
	@Test
	public void testJSON() throws WriterException, MappingException, ReaderException {
		BinderWriter writer=BinderFactory.getJSONWriter();
		String buffer=writer.write(bean);
		System.out.println(buffer);
		
		BinderReader reader=BinderFactory.getJSONReader();
		Bean05 bean2=reader.read(Bean05.class, buffer);
		String buffer2=writer.write(bean2);
		System.out.println(buffer2);
	}

	
	@Test
	public void testXMLSAX() throws WriterException, MappingException, ReaderException {
		BinderWriter writer=BinderFactory.getXMLWriter();
		String buffer=writer.write(bean);
		System.out.println(buffer);
		
		BinderFactory.readerType=ReaderType.SAX;
		BinderReader reader=BinderFactory.getXMLReader();
		Bean05 bean2=reader.read(Bean05.class, buffer);
		String buffer2=writer.write(bean2);
		System.out.println(buffer2);
	}
	
	@Test
	public void testXMLDOM() throws WriterException, MappingException, ReaderException {
		
		BinderWriter writer=BinderFactory.getXMLWriter();
		String buffer=writer.write(bean);
		System.out.println(buffer);
		
		BinderFactory.readerType=ReaderType.DOM;
		BinderReader reader=BinderFactory.getXMLReader();
		Bean05 bean2=reader.read(Bean05.class, buffer);
		String buffer2=writer.write(bean2);
		System.out.println(buffer2);
	}
}
