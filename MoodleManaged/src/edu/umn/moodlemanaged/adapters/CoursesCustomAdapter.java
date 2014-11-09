package edu.umn.moodlemanaged.adapters;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import edu.umn.moodlemanaged.Course;
import edu.umn.moodlemanaged.Notifications;
import edu.umn.moodlemanaged.R;

public class CoursesCustomAdapter extends ArrayAdapter<Course> {
	Context context;
	int layoutResourceId;
	ArrayList<Course> courses = new ArrayList<Course>();

	public CoursesCustomAdapter(Context context, int layoutResourceId, ArrayList<Course> courses) {
		super(context, layoutResourceId, courses);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.courses = courses;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		CourseHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new CourseHolder();
			holder.courseName = (TextView) row.findViewById(R.id.course_view_course_name);
			holder.courseID = (TextView) row.findViewById(R.id.course_view_course_id);
			holder.btnNotifications = (Button) row.findViewById(R.id.notifications_bubble);
			holder.btnSyllabus = (Button) row.findViewById(R.id.syllabus_btn);
			holder.btnOfficeHours = (Button) row.findViewById(R.id.office_hours_btn);
			row.setTag(holder);
		} else {
			holder = (CourseHolder) row.getTag();
		}
		
		Course course = courses.get(position);
		holder.courseName.setText(course.getName());
		holder.courseID.setText(course.getID());
		
		holder.btnNotifications.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("btnNotifications Button Clicked", "**********");
				Toast.makeText(context, "Notifications button Clicked",
						Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, Notifications.class);
                context.startActivity(intent);
			}
		});
		
		holder.btnSyllabus.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("btnSyllabus Button Clicked", "**********");
				/*Toast.makeText(context, "Syllabus button Clicked",
						Toast.LENGTH_LONG).show();*/

                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +"/"+ "syllabus.pdf");
                Intent target = new Intent(Intent.ACTION_VIEW);
                target.setDataAndType(Uri.fromFile(file), "application/pdf");
                target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                Intent intent = Intent.createChooser(target, "Open File");
                try {
                    context.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Instruct the user to install a PDF reader here, or something
                }

            }
		});
		
		holder.btnOfficeHours.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("Delete Button Clicked", "**********");
				Toast.makeText(context, "Office Hours button Clicked",
						Toast.LENGTH_LONG).show();
			}
		});
		
		return row;

	}

    // Holder used to build row from xml and data
	static class CourseHolder {
		TextView courseID;
		TextView courseName;
		Button btnNotifications;
		Button btnSyllabus;
		Button btnOfficeHours;
	}
}
