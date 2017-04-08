package com.peacecorps.pcsa.get_help_now;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.peacecorps.pcsa.MainActivity;
import com.peacecorps.pcsa.R;

/**
 * Allows user to select the Other Staff member to contact
 * Buttons in the screen correspond to various office members
 *
 * @author Buddhiprabha Erabadda
 * @since 07-08-2015
 */
public class ContactOtherStaff extends Fragment {

    public static final String TAG = ContactOtherStaff.class.getSimpleName() ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_reporting_contact_other_staff,container,false);
        Button contactPcSaves = (Button) rootView.findViewById(R.id.pcsaves); //Button to access PC Saves Anonymous HelpLine
        Button contactOva = (Button) rootView.findViewById(R.id.ova); //Button to access Office of Victim Advocacy
        Button contactOig = (Button) rootView.findViewById(R.id.oig); //Button to access Office of Inspector General
        Button contactOcrd = (Button) rootView.findViewById(R.id.ocrd); //Button to access Office of Civil Rights and diversity
        ImageView contactPostStaff = (ImageView) rootView.findViewById(R.id.link_to_post_staff);

        contactPcSaves.setText(R.string.contact_pcsaves);
        contactOva.setText(R.string.contact_ova);
        contactOig.setText(R.string.contact_oig);
        contactOcrd.setText(R.string.contact_ocrd);

        contactPcSaves.setOnClickListener(contactOnClickListener);
        contactOva.setOnClickListener(contactOnClickListener);
        contactOig.setOnClickListener(contactOnClickListener);
        contactOcrd.setOnClickListener(contactOnClickListener);

        contactPostStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Removing ContactOtherStaff from the fragment container dynamically
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.popBackStack(ContactOtherStaff.TAG,FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.reporting_get_help);
        return rootView;
    }

    /**
     * the onClickListner for all Other Staff contacts
     */
    private View.OnClickListener contactOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Fragment otherStaffContent;
            Bundle args;
            switch (v.getId()){
                case R.id.pcsaves:
                    //Swapping OtherStaffContent into the fragment container dynamically
                    otherStaffContent = new OtherStaffContent();
                    args = new Bundle();
                    args.putString(OtherStaffContent.CONTACT_NAME, getResources().getString(R.string.contact_pcsaves));
                    args.putString(OtherStaffContent.CONTACT_DESC_PART1, getResources().getString(R.string.reporting_desc_pcsaves_part1));
                    args.putString(OtherStaffContent.CONTACT_DESC_PART2, getResources().getString(R.string.reporting_desc_pcsaves_part2));
                    args.putString(OtherStaffContent.CONTACT_DESC_PART3, "");
                    args.putString(OtherStaffContent.URL1, getResources().getString(R.string.reporting_desc_pcsaves_url));
                    args.putString(OtherStaffContent.URL2, "");
                    args.putString(OtherStaffContent.CONTACT_NUMBER, getResources().getString(R.string.reporting_contact_pcsaves));
                    otherStaffContent.setArguments(args);
                    MainActivity.swapFragmentIn(getActivity(),otherStaffContent,OtherStaffContent.TAG,true);
                    break;
                case R.id.ova:
                    //Swapping OtherStaffContent into the fragment container dynamically
                    otherStaffContent = new OtherStaffContent();
                    args = new Bundle();
                    args.putString(OtherStaffContent.CONTACT_NAME, getResources().getString(R.string.contact_ova));
                    args.putString(OtherStaffContent.CONTACT_DESC_PART1, getResources().getString(R.string.reporting_desc_ova_part1));
                    args.putString(OtherStaffContent.CONTACT_DESC_PART2, getResources().getString(R.string.reporting_desc_ova_part2));
                    args.putString(OtherStaffContent.CONTACT_DESC_PART3, "");
                    args.putString(OtherStaffContent.URL1, getResources().getString(R.string.reporting_desc_ova_url));
                    args.putString(OtherStaffContent.URL2, "");
                    args.putString(OtherStaffContent.CONTACT_NUMBER, getResources().getString(R.string.reporting_contact_ova));
                    otherStaffContent.setArguments(args);
                    MainActivity.swapFragmentIn(getActivity(),otherStaffContent,OtherStaffContent.TAG,true);
                    break;
                case R.id.oig:
                    //Swapping OtherStaffContent into the fragment container dynamically
                    otherStaffContent = new OtherStaffContent();
                    args = new Bundle();
                    args.putString(OtherStaffContent.CONTACT_NAME, getResources().getString(R.string.contact_oig));
                    args.putString(OtherStaffContent.CONTACT_DESC_PART1, getResources().getString(R.string.reporting_desc_oig_part1));
                    args.putString(OtherStaffContent.CONTACT_DESC_PART2, getResources().getString(R.string.reporting_desc_oig_part2));
                    args.putString(OtherStaffContent.CONTACT_DESC_PART3, getResources().getString(R.string.reporting_desc_oig_part3));
                    args.putString(OtherStaffContent.URL1, getResources().getString(R.string.reporting_desc_oig_url1));
                    args.putString(OtherStaffContent.URL2, getResources().getString(R.string.reporting_desc_oig_url2));
                    args.putString(OtherStaffContent.CONTACT_NUMBER, getResources().getString(R.string.reporting_contact_oig));
                    otherStaffContent.setArguments(args);
                    MainActivity.swapFragmentIn(getActivity(),otherStaffContent,OtherStaffContent.TAG,true);
                    break;
                case R.id.ocrd:
                    //Swapping OtherStaffContent into the fragment container dynamically
                    otherStaffContent = new OtherStaffContent();
                    args = new Bundle();
                    args.putString(OtherStaffContent.CONTACT_NAME, getResources().getString(R.string.contact_ocrd));
                    args.putString(OtherStaffContent.CONTACT_DESC_PART1, getResources().getString(R.string.reporting_desc_ocrd_part1));
                    args.putString(OtherStaffContent.CONTACT_DESC_PART2, getResources().getString(R.string.reporting_desc_ocrd_part2));
                    args.putString(OtherStaffContent.CONTACT_DESC_PART3, "");
                    args.putString(OtherStaffContent.URL1, getResources().getString(R.string.reporting_desc_ocrd_url));
                    args.putString(OtherStaffContent.URL2, "");
                    args.putString(OtherStaffContent.CONTACT_NUMBER, getResources().getString(R.string.reporting_contact_ocrd));
                    otherStaffContent.setArguments(args);
                    MainActivity.swapFragmentIn(getActivity(),otherStaffContent,OtherStaffContent.TAG,true);
                    break;
            }
        }
    };
}
