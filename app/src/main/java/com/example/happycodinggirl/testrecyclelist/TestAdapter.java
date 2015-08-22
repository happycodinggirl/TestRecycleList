package com.example.happycodinggirl.testrecyclelist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lily on 15-8-21.
 */
public class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	ArrayList<String> strings;

	static int TYPE_FOOTER =0;
	static int TYPE_CONTENT=1;
	private boolean loadMoreShow=false;


	public TestAdapter(ArrayList<String> strings) {
		this.strings = strings;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater=LayoutInflater.from(parent.getContext());

		View v=inflater.inflate(R.layout.item, parent, false);
		if(viewType== TYPE_FOOTER){

			ContentHolder titleHolder=new ContentHolder(v);
		//	ViewStub viewStub= (ViewStub) v.findViewById(R.id.viewstub);

			if (titleHolder.tv_footer ==null){
				titleHolder.inflatedView=titleHolder.viewStub.inflate();
				TextView footer= (TextView) titleHolder.inflatedView.findViewById(R.id.footer);
				footer.setText("i am inflater tv_footer");

			}else{
				titleHolder.inflatedView.setVisibility(View.VISIBLE);
				titleHolder.tv_footer.setVisibility(View.VISIBLE);
			}

			if (isLoadMoreShow()){
				titleHolder.tv_footer.setVisibility(View.VISIBLE);
			}else{
				titleHolder.tv_footer.setVisibility(View.GONE);
			}
			return titleHolder;

		}else{


			ContentHolder titleHolder=new ContentHolder(v);
			return titleHolder;

		}


	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		ContentHolder titleHolder= (ContentHolder) holder;


		titleHolder.button.setText(strings.get(position));


	}

	public void setLoadMoreShow(boolean loadMoreShow){
		this.loadMoreShow=loadMoreShow;
	}

	public boolean isLoadMoreShow() {
		return loadMoreShow;
	}

	@Override
	public int getItemViewType(int position) {
		if (position+1==getItemCount()){
			return TYPE_FOOTER;
		}else{
			return TYPE_CONTENT;
		}
	}

	@Override
	public int getItemCount() {
		return strings.size();
	}


	static class ContentHolder extends RecyclerView.ViewHolder {

		View view;
		@Bind(R.id.viewstub)
		ViewStub viewStub;
		@Bind(R.id.button) Button button;

		View inflatedView;

		TextView tv_footer;




		public ContentHolder(View itemView) {
			super(itemView);
			this.view=itemView;
			ButterKnife.bind(this, view);


		}
	}
}
