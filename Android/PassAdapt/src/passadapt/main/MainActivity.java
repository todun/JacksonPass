package passadapt.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.example.passadapt.R;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.Xml;
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnGenericMotionListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.TableLayout;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.SlidingDrawer.OnDrawerScrollListener;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final MediaPlayer close = MediaPlayer.create(getApplicationContext(),
				R.raw.open2);
		final MediaPlayer open = MediaPlayer.create(getApplicationContext(),
				R.raw.close2);

		final SlidingDrawer handle = (SlidingDrawer) findViewById(R.id.slidingDrawer1);

		createRows();
		LinearLayout ll = (LinearLayout) findViewById(R.id.linearlayout);
		final ImageView iv = new ImageView(getApplicationContext());
		iv.setPadding(50, 30, 0, 0);
		iv.setImageDrawable(getResources().getDrawable(R.drawable.lock2));
		ll.addView(iv);
		iv.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				iv.setImageDrawable(getResources().getDrawable(
						R.drawable.unlock2));
				handle.animateOpen();

			}
		});

		handle.setOnDrawerCloseListener(new OnDrawerCloseListener() {

			public void onDrawerClosed() {
				close.start();
				iv.setImageDrawable(getResources()
						.getDrawable(R.drawable.lock2));

			}
		});
		handle.setOnDrawerOpenListener(new OnDrawerOpenListener() {

			public void onDrawerOpened() {
				open.start();

			}
		});

		handle.setOnDrawerScrollListener(new OnDrawerScrollListener() {

			public void onScrollStarted() {
				iv.setImageDrawable(getResources().getDrawable(
						R.drawable.unlock2));

			}

			public void onScrollEnded() {

			}
		});

	}

	public void parse() throws DocumentException {
		SAXReader reader = new SAXReader();
		// Get the xml document object by sax reader.
		Document document = reader.read("assets/sample.xml");
		// Define the xpath
		String xpathExpression = "//categories/category/blocks/block/traits";
		// Get the list of nodes on given xPath
		List<? extends Node> nodes = document.selectNodes(xpathExpression);

		// Read all the node inside xpath nodes and print the value of each
		for (Node node : nodes) {
			Node id = node.selectSingleNode("trait");
			System.out.println("Person Id : " + id.getText());
		}
	}

	public void createRows() {
		parse();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Astros wins", "13");
		map.put("DOW JONES", "+10.44%");

		TableLayout table = (TableLayout) findViewById(R.id.tablelayout);
		for (String key : map.keySet()) {
			TableRow row = new TableRow(getApplicationContext());
			TextView text = new TextView(getApplicationContext());
			text.setText(key + ": " + map.get(key));
			new Color();
			text.setTextColor(Color.rgb(92, 62, 36));
			text.setTextSize(23);
			text.setPadding(0, 10, 0, 0);
			text.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);
			row.addView(text);
			table.addView(row);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
