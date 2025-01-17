import java.io.IOException;
import java.util.*;
import java.text.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class twitterMapper extends Mapper<Object, Text, Text, IntWritable> {
    private IntWritable counter = new IntWritable(1);
    private Text hour = new Text();


    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
    String[] fields = value.toString().split(";",-1);
	try{
	if(fields.length == 4){
        	long epochtime = Long.parseLong(fields[0]);
          Date date = new Date(epochtime);
          SimpleDateFormat sdf = new SimpleDateFormat("HH");
          sdf.setTimeZone(TimeZone.getTimeZone("GMT-3"));
		      String dateformatted = sdf.format(date);
		      hour.set(dateformatted);
    	    context.write(hour, counter);}
	}catch(NumberFormatException nfe){
		}
    }
  }
