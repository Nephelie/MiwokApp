package com.example.android.miwok;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nefeli on 9/5/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    /** Resource ID for the background color for this list of words */
    private int mColorResourceId;


    /**
     * Create a new {@link WordAdapter} object.
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param pWords is the list of {@link Word}s to be displayed.
     * @param colorResourceId is the resource ID for the background color for this list of words
     */
    public WordAdapter(Context context, ArrayList<Word> pWords,int colorResourceId) {
        super(context,0, pWords);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Word local_word = getItem(position);



        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(local_word.getMiwokTranslation());

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(local_word.getDefaultTranslation());

        // Find the ImageView in the list_item.xml layout with the ID image.
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        // Check if an image is provided for this word or not
              if (local_word.hasImage()) {
                        // If an image is available, display the provided image based on the resource ID
                               imageView.setImageResource(local_word.getImageResourceId());
                        // Make sure the view is visible
                                imageView.setVisibility(View.VISIBLE);
                   } else {
                        // Otherwise hide the ImageView (set visibility to GONE)
                                imageView.setVisibility(View.GONE);
                 }

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }

}

