package se.fritzon.editeducation;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import se.fritzon.editeducation.utils.CourseInfo;
import se.fritzon.editeducation.utils.ExpandableListAdapter;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;


public class MainActivity extends Activity {
 
    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> educationCollection;
    ExpandableListView expListView;
    String link;
    
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);	
        
        
        contactinfo();
        createGroupList();
        createCollection();
        
 
        expListView = (ExpandableListView) findViewById(R.id.education_list);
        final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
                this, groupList, educationCollection);
        expListView.setAdapter(expListAdapter);
 
        
       
        expListView.setOnChildClickListener(new OnChildClickListener() {
 
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
               
            	final String selected = (String) expListAdapter.getChild(
                        groupPosition, childPosition);
                
                
                Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG)
                        .show();
               
         
         String s = expListAdapter.getChild(groupPosition, childPosition).toString();
           
          try {
				link = CourseInfo.Courselink(s);
				
			} catch (MalformedURLException e) {
				Log.d("url link is broken and document cannot be loaded.", link);
				e.printStackTrace();
			}
               
               Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(link));
                
                startActivity(intent);
               
                return true;
            }
        });
    }
 
    private void createGroupList() {
        groupList = new ArrayList<String>();
        groupList.add("Windows");
        groupList.add("Outlook");
        groupList.add("Word");
        groupList.add("Excel");
        groupList.add("PowerPoint");
        groupList.add("Access");
        groupList.add("MS-Projekt");
        groupList.add("Visio");
        groupList.add("SharePoint");
        groupList.add("Photoshop");
        groupList.add("Adobe");
        groupList.add("MS-Office");
    }
    //TODO check states and sorting
    private void createCollection() {
        
        String[] OutlookModels = { "Outlook" };
        String[] PowerPointModels = { "PowerPoint grundkurs" };
        String[] WordModels = { "Word Grundkurs", "Word Fortsättningskurs" };
        String[] ExcelModels = { "Excel Grundkurs", "Excel Fortsättningskurs", "Excel för ekonomer", "Excel listor samt register" };
        String[] WindowsModels = { "Windows 7", "Windows 8" };
        String[] AccessModels = { "Access" };
        String[] MsProjectModels = { "Projekt Grundkurs" };
        String[] VisioModels = { "Visio Grundkurs" };
        String[] SharePointModels = { "SharePoint för redaktörer" };
        String[] PhotoshopModels = { "Photoshop Grundkurs", "Photoshop elements" };
        String[] AdobeModels = { "Acrobat Grundkurs", "Indesign Grundkurs" };
        String[] MsOfficeModels = { "Nyheter i Office", "Publisher", "OneNote Grundkurs" };
        
        educationCollection = new LinkedHashMap<String, List<String>>();
 
        for (String courses : groupList) {
            if (courses.equals("Outlook")) {
                loadChild(OutlookModels);
            } else if (courses.equals("Windows"))
                loadChild(WindowsModels);
            else if (courses.equals("Excel"))
                loadChild(ExcelModels);
            else if (courses.equals("PowerPoint"))
                loadChild(PowerPointModels);
            else if (courses.equals("Access"))
                loadChild(AccessModels);
            else if (courses.equals("MS-Projekt"))
            	loadChild(MsProjectModels);
            else if (courses.equals("Visio"))
            	loadChild(VisioModels);
            else if (courses.equals("SharePoint"))
            	loadChild(SharePointModels);
            else if (courses.equals("Photoshop"))
            	loadChild(PhotoshopModels);
            else if (courses.equals("Adobe"))
            	loadChild(AdobeModels);
            else if (courses.equals("MS-Office"))
            	loadChild(MsOfficeModels);
            else
                loadChild(WordModels);
 
            educationCollection.put(courses, childList);
        }
    }
 
    private void loadChild(String[] courseModels) {
        childList = new ArrayList<String>();
        for (String model : courseModels)
            childList.add(model);
    }

 
    //TODO sort contact info into presaved Strings, sort a ahref link too editab.se
   private void contactinfo () {
	   CheckedTextView textviewContact = (CheckedTextView)findViewById(R.id.contacttext);
       CheckedTextView textviewInfo = (CheckedTextView)findViewById(R.id.welcometext);
       CheckedTextView textviewHTML = (CheckedTextView)findViewById(R.id.htmlinks);
       
      textviewInfo.setText(getText(R.string.welcome_message) + "");
      textviewContact.setText(getText(R.string.contact_info) + "");
      
      textviewHTML.setText(Html.fromHtml(getString(R.string.html)));
      textviewHTML.setMovementMethod(LinkMovementMethod.getInstance());
       
   }
    
    
    
}