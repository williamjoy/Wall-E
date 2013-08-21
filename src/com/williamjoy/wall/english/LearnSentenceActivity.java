package com.williamjoy.wall.english;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.williamjoy.wall.english.view.VocabularyInput;

public class LearnSentenceActivity extends Activity {
    String learn_content = "Nowadays, the increasing rate of overweight children and adults is a worldwide health issue. Obesity is a major problem which is increasing day by day in school-age children. There are various reasons behind it. This essay will discuss the causes of obesity and offer some solutions.\n"
            + "\n"
            + "The first cause of obesity is junk food. It is often seen that mostly children are fond of burgers, pizzas, noodles and coke. These types of foods are easily available to them in school canteens. Children love to purchase chips, chocolates, - ice-cream for lunch. Moreover, in this modern era, parents are working and they do not have time to cook at home. Parents often buy dinner for their children instead of preparing food at home. This calorie-rich diet is making children obese. This problem can be solved by teaching children to cook healthy foods for themselves and banning junk foods and fizzy drinks in schools. This diet can be replaced by milk, juice and fruits for lunch.\n"
            + "\n"
            + "The second cause of obesity is sedentry life style. It is true that the use of computers and television is increasing in children. They spend most of their time watching television or playing video games on a computer. This technological advancement has reduced the level of physical activity in this specific age group. This issue can be resolved by encouraging children to do physical exercises. Parents can take their children to park to encourage playing with friends. Furthermore, schools can add sports in their curriculum to maintain physical fitness in their students.\n"
            + "\n"
            + "To sum up, it is clear that main causes of obesity are unhealthy eating and not enough physical activities. This ailment can be prevented and treated by healthy eating habbits and physical exercises.\n"
            + "\n"
            + "This is a good essay. There are only a few minor errors that could have been easily prevented by proofreading this essay one last time before submission (mouse over the words underlined in blue shows corrections). Overall, this work seems worthy of IELTS Band 8. Keep up the good work!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_sentence);
        TextView t=(TextView) this.findViewById(R.id.given_content);
        t.setText(learn_content);
        Pattern p = Pattern.compile("(?i)([a-z0-9]+(-[a-z0-9]+)?)|[,.]+");
        Matcher m = p.matcher(learn_content);
        ViewGroup l = (ViewGroup) this.findViewById(R.id.wrapper_layout);

        while (m.find()) { // Find each match in turn; String can't do this.
            String token = m.group(0); // Access a submatch group; String can't do this.
            l.addView(new VocabularyInput(getApplicationContext(), token));
        }
//        for (String token : learn_content.split("[ \\t\\n]+")) {
//            l.addView(new VocabularyInput(getApplicationContext(), token));
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_learn_sentence, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.menu_settings):
                Intent intent = new Intent(this.getApplicationContext(),
                        SettingsActivity.class);
                startActivityForResult(intent, 0);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
