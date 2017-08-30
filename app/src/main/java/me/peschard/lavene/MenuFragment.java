package me.peschard.lavene;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import me.peschard.lavene.entities.Product;

public class MenuFragment extends Fragment {
    private static final String ARG_TEXT = "arg_text";
    private static final String ARG_COLOR = "arg_color";

    //    private String mText;
    private int mColor;
    //    private TextView mTextView;
    private String mPath;

    private View mContent;
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mLayoutManager;
    private TextView noItems;

    //Getting reference to Firebase Database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mDatabaseReference = database.getReference();

    public static Fragment newInstance(String text, int color) {
        Fragment frag = new MenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        args.putInt(ARG_COLOR, color);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // retrieve text and color from bundle or savedInstanceState
        if (savedInstanceState == null) {
            Bundle args = getArguments();
            mPath = args.getString(ARG_TEXT);
            mColor = args.getInt(ARG_COLOR);
        } else {
            mPath = savedInstanceState.getString(ARG_TEXT);
            mColor = savedInstanceState.getInt(ARG_COLOR);
        }

        // initialize views
        mContent = view.findViewById(R.id.fragment_content);
        mRecyclerView = view.findViewById(R.id.my_recycler_view);
//        mTextView = view.findViewById(R.id.text);
        noItems = view.findViewById(R.id.no_items);

        // setup views

        mContent.setBackgroundColor(mColor);
//        mTextView.setText(mPath);
        if (mRecyclerView != null) {
            //to enable optimization of recyclerview
            mRecyclerView.setHasFixedSize(true);
        }

        //using staggered grid pattern in recyclerview
        mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        final FirebaseRecyclerAdapter<Product,ProductViewHolder> adapter = new FirebaseRecyclerAdapter<Product, ProductViewHolder>(
                Product.class,
                R.layout.product_list_item,
                ProductViewHolder.class,
                mDatabaseReference.child(mPath).getRef()
        ) {
            @Override
            protected void populateViewHolder(ProductViewHolder viewHolder, Product model, int position) {
                if (noItems.getVisibility() == View.VISIBLE) {
                    noItems.setVisibility(View.GONE);
                }
                viewHolder.productName.setText(model.getName());
                viewHolder.productDesc.setText(model.getDesc());


                // Reference to an image file in Cloud Storage
                FirebaseStorage storage = FirebaseStorage.getInstance();
                String imagePath = "productImages/" + model.code + ".jpg";
                StorageReference storageRef = storage.getReference().child(imagePath);

                // Load the image using Glide
                Glide.with(getContext())
                        .using(new FirebaseImageLoader())
                        .load(storageRef)
                        .into(viewHolder.productImage);
            }
        };
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ARG_TEXT, mPath);
        outState.putInt(ARG_COLOR, mColor);
        super.onSaveInstanceState(outState);
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        if (fab.getVisibility() == View.GONE)
//            fab.setVisibility(View.VISIBLE);
//    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView productName;
        TextView productDesc;
        ImageView productImage;

        public ProductViewHolder(View v) {
            super(v);
            productName = v.findViewById(R.id.product_list_name);
            productDesc = v.findViewById(R.id.product_list_desc);
            productImage = v.findViewById(R.id.product_list_image);
        }

    }
}
