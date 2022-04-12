package bd.dkltd.codeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.EditText;
import android.widget.Toast;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.text.TextWatcher;
import android.text.Editable;

public class CodeEditor extends LinearLayout {

	// supported languages
	public static final int LANGUAGE_TEXT = 0;
	public static final int LANGUAGE_HTML = 1;
	public static final int LANGUAGE_MARKDOWN = 2;
	public static final int LANGUAGE_CSS = 3;
	public static final int LANGUAGE_JAVASCRIPT = 4;
	public static final int LANGUAGE_PHP = 5;
	
	// default values
	public static final int DEFAULT_LANG = LANGUAGE_TEXT;
	public static final boolean DEFAULT_SHOW_LINE_NUMBER = false;
	public static final boolean DEFAULT_CHECK_SYNTAX_ERROR = false;
	public static final boolean DEFAULT_TEXT_WRAP = false;

	// variable declaration
	private int languageMode;
	private String size;
	private boolean showLineNumber,checkSyntaxError,textWrap;
	
	// inner component
	private View editorView;
	private LinearLayout editorRoot, editorParent, lineGutter, everyLine;
	private ScrollView scrollView;
	private HorizontalScrollView hsView;
	private RelativeLayout rlayout;
	private TextView lineNumberTV;
	private ImageView synChkImgView, codeBlockImgView;
	private EditText mainEditor;
	private boolean hasMinimumSet;
	
	//constructor
	public CodeEditor(Context context) {
		super(context);
		mainTask(context);
	}

    public CodeEditor(Context context, AttributeSet attrs) {
        super(context, attrs);
		activateAttributes(context, attrs);
		mainTask(context);
    }

	// public method
	public void setLanguageMode(int languageMode) {
		this.languageMode = languageMode;
		//important
		invalidate();
		requestLayout();
	}

	public int getLanguageMode() {
		return languageMode;
	}

	// private method
	private void activateAttributes(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CodeEditor, 0, 0);
		try {
			languageMode = a.getInteger(R.styleable.CodeEditor_languageMode, LANGUAGE_TEXT);
			showLineNumber = a.getBoolean(R.styleable.CodeEditor_showLineNumber, DEFAULT_SHOW_LINE_NUMBER);
			checkSyntaxError = a.getBoolean(R.styleable.CodeEditor_checkSyntaxError, DEFAULT_CHECK_SYNTAX_ERROR);
			textWrap = a.getBoolean(R.styleable.CodeEditor_enableTextWrap, DEFAULT_TEXT_WRAP);
		} finally {
			a.recycle();
		}
	}
	private void mainTask(Context context) {
		//init all
		LayoutInflater inflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		editorView = inflater.inflate(R.layout.layout_ceditor, this);
	}
	
	// overriden method
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		// init all
		editorRoot = editorView.findViewById(R.id.editorRoot); //has default (300×300)dp size
		scrollView = editorView.findViewById(R.id.rootScrollView);
		hsView = editorView.findViewById(R.id.rootHorizontalScrollView);
		editorParent = editorView.findViewById(R.id.editorParent);
		lineGutter = editorView.findViewById(R.id.editorParent);
		everyLine = editorView.findViewById(R.id.everyLine);
		lineNumberTV = editorView.findViewById(R.id.lineNmbrTv);
		synChkImgView = editorView.findViewById(R.id.synChkImgView);
		codeBlockImgView = editorView.findViewById(R.id.codeBlockImgView);
		rlayout = editorView.findViewById(R.id.editorAreaParent);
		
		mainEditor = editorView.findViewById(R.id.mainEditor);
		hasMinimumSet = false;
		mainEditor.setOnKeyListener(new View.OnKeyListener() {

				@Override
				public boolean onKey(View v1, int keyCode, KeyEvent event) {
					if (event.getAction() == KeyEvent.ACTION_DOWN) {
						// Manage KeyBinding
					} else if (event.getAction() == KeyEvent.ACTION_UP) {
						// manage auto complete
					}
					return false;
				}
			});
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (!hasMinimumSet) {
			hasMinimumSet = true;
			mainEditor.setMinimumWidth(rlayout.getWidth());
			mainEditor.setMinimumHeight(rlayout.getHeight());
			//Toast.makeText(getContext(), "onLayout", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
	}
	
	//Save and restore state
	@Override
	protected Parcelable onSaveInstanceState() {
		return super.onSaveInstanceState();
	}

	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		super.onRestoreInstanceState(state);
	}

	@Override
	protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
		super.dispatchSaveInstanceState(container);
	}

	@Override
	protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
		super.dispatchRestoreInstanceState(container);
	}

}
