package nguyengiap.vietitpro.tudienanhviet.com.fragment;


import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.text.ClipboardManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import nguyengiap.vietitpro.tudienanhviet.com.R;
import nguyengiap.vietitpro.tudienanhviet.com.common.Common;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSearchDocument extends Fragment implements View.OnClickListener {


    private EditText etInputText;
    private TextView txtOutPutText;
    private LinearLayout btnGoogle;
    private ImageView btnDeleteText;
    private TextView btnTranslator_AV, btnTranslator_VA;
    private ImageView btnCoppy;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mMainLayout = inflater.inflate(R.layout.fragment_fragment_search_document, container, false);
        initView(mMainLayout);

        // Inflate the layout for this fragment
        return mMainLayout;
    }

    private void initView(View mMainLayout) {
        etInputText = (EditText) mMainLayout.findViewById(R.id.et_inputText);
        txtOutPutText = (TextView) mMainLayout.findViewById(R.id.txt_OutputText);
        btnGoogle = (LinearLayout) mMainLayout.findViewById(R.id.btnGoogle);
        btnDeleteText = (ImageView) mMainLayout.findViewById(R.id.btnDeleteText);
        btnCoppy = (ImageView) mMainLayout.findViewById(R.id.btnCoppy);
        btnTranslator_AV = (TextView) mMainLayout.findViewById(R.id.btnTranslator_AV);
        btnTranslator_VA = (TextView) mMainLayout.findViewById(R.id.btnTranslator_VA);
        btnTranslator_AV.setOnClickListener(this);
        btnTranslator_VA.setOnClickListener(this);
        btnCoppy.setOnClickListener(this);
        btnGoogle.setOnClickListener(this);
        btnDeleteText.setOnClickListener(this);
        etInputText.addTextChangedListener(textWatcher);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTranslator_AV:
                eventTraslate(etInputText.getText().toString(),0);
                break;
            case R.id.btnTranslator_VA:
                eventTraslate(etInputText.getText().toString(),1);
                break;
            case R.id.btnCoppy:
                eventCopyTextResult();
                break;
            case R.id.btnGoogle:
                promptSpeechInput();
                break;
            case R.id.btnDeleteText:
                etInputText.setText("");
                btnGoogle.setVisibility(View.VISIBLE);
                btnDeleteText.setVisibility(View.GONE);
                break;
        }
    }

    private void eventCopyTextResult() {
        Common.showToast(getActivity().getString(R.string.copy));
        @SuppressWarnings("deprecation")
        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.setText(txtOutPutText.getText().toString());
    }

    private void eventTraslate(final String word, final int chooseTraslate) {

        try {
            if (!word.isEmpty()) {
                Common.hideSoftInputFromWindow(getActivity());
                final ProgressDialog ringProgressDialog = ProgressDialog.show(getContext(), "", "Please Waitting...");
                ringProgressDialog.setCancelable(true);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String strResult = Common.UpdateTranslate(word, chooseTraslate);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txtOutPutText.setText(strResult);
                                ringProgressDialog.dismiss();
                            }
                        });
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private final int REQ_CODE_SPEECH_INPUT = 100;

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getContext(), "Error",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            switch (requestCode) {
                case REQ_CODE_SPEECH_INPUT: {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (!result.get(0).isEmpty())
                    etInputText.setText(result.get(0));
                    eventTraslate(result.get(0),0);
                    break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            try {
                if (s.length() > 0) {
                    btnGoogle.setVisibility(View.GONE);
                    btnDeleteText.setVisibility(View.VISIBLE);
                } else {
                    btnGoogle.setVisibility(View.VISIBLE);
                    btnDeleteText.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

}
