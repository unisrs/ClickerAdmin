package com.clicker.admin;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;


public class ButtonWidget extends ToggleButton implements BuilderWidget{
	
	private Dialog editorDialog;
	private EditText labelText;
	private CheckBox enabledCheck;
	private RelativeLayout editingLayout;
	private LinearLayout editFieldLayout;
	private Button saveWidgeButton;
	private QuestionEditor seContext;
	private boolean wasCreated;
	
	
	public ButtonWidget(QuestionEditor context) {
		super(context);
		seContext = context;
		editorDialog = new Dialog(context);
		
		setText("");
		setTextOn("");
		setTextOff("");
		
		setBackgroundResource(android.R.drawable.btn_default);
		
		TextView labelLabel = new TextView(context);
		labelLabel.setText("Label");
		labelText = new EditText(context);
		TextView enabledLabel = new TextView(context);
		enabledLabel.setText("Default");
		enabledCheck = new CheckBox(context);
		editingLayout = new RelativeLayout(context);
		editFieldLayout = new LinearLayout(context);
		editFieldLayout.setOrientation(LinearLayout.VERTICAL);
		saveWidgeButton = new Button(context);
		saveWidgeButton.setText("Save values");
		RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		buttonParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

		editFieldLayout.addView(labelLabel, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		editFieldLayout.addView(labelText, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		editFieldLayout.addView(enabledLabel, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		editFieldLayout.addView(enabledCheck,new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		editingLayout.addView(editFieldLayout, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT, 1f));
		editingLayout.addView(saveWidgeButton, buttonParams);
		saveWidgeButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				saveValues();
			}
		});
		editorDialog.setContentView(editingLayout);
		wasCreated = true;
	}
	
	public void doneCreating() {
		wasCreated = false;
	}
	
	public void setValues(String qString) {
		String[] widgetParts = qString.split("`/:");
		setText(widgetParts[1]);
	    setTextOn(widgetParts[1]);
	    setTextOff(widgetParts[1]);
	    
		setSelected(widgetParts[2].equals("1"));
		seContext.widgetEdited(this, wasCreated);
	}
	
	public String getValue() {
		String selected = "";
		if (isSelected()) {
			selected = "1";
		} else {
			selected = "0";
		}
		return "B`/:" + getText().toString() + "`/:" + selected;
	}
	
	public void showEditingDialog() {	
		labelText.setText(getText());
		enabledCheck.setChecked(isSelected());
		editorDialog.show();
	}
	
	public void saveValues() {
		setSelected(enabledCheck.isChecked());
		setText(labelText.getText());
		setTextOn(labelText.getText());
		setTextOff(labelText.getText());
		seContext.widgetEdited(this, wasCreated);
		editorDialog.dismiss();
	}
	
}
