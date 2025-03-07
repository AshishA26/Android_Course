package com.ashish.ashishsandroidprojects;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

// Making our own adapter, instead of using ArrayAdapter.
public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ProjectViewHolder> {
    // For <>, VH extends ViewHolder

    // Need to give project array to projectAdapter, like arrayAdapter
    private Project[] projects;

    // Constructor:
    public ProjectsAdapter(Project[] projects) {
        this.projects = projects;
    }

    // Adapter is abstract class, and has abstract functions that we must override

    // How many items in the adapter.
    @Override
    public int getItemCount() {
        return projects.length; //Length of array
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // There is no setContentView method here, so we need to instead "inflate" the layout file, the R.layout.item_project.
        // When you do setContentView(R.layout.activity_main), what's actually happening is you are inflating the layout file.
        // You are building the code so that it will show on our screen.

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false);
        return new ProjectViewHolder(view); // Make a new viewHolder
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        holder.bind(projects[position]); // Binding each project to a view, in the view holder
    }

    // Static means don't need to have a new object to be able to access the class
    // ViewHolder will be class which will hold individual views, allows for recycling of views
    static class ProjectViewHolder extends RecyclerView.ViewHolder {

        private ImageView appImage;
        private TextView appTitle;
        private TextView appDescription;

        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            // Need to do itemView because ViewHolder is not immediately a context, can't do it natively
            appImage = itemView.findViewById(R.id.image_view_project_icon);
            appTitle = itemView.findViewById(R.id.text_view_project_title);
            appDescription = itemView.findViewById(R.id.text_view_project_description);
        }

        // Bind 1 project
        public void bind(Project project) {
            appTitle.setText(project.name);
            appDescription.setText(project.description);
            appImage.setImageResource(project.image);
        }

    }
}
