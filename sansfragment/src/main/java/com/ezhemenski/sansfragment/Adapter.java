package com.ezhemenski.sansfragment;

import androidx.annotation.NonNull;

interface Adapter<S> {
    @NonNull
    ViewHolder createViewHolder(@NonNull S screen, @NonNull Navigator<S> navigator);
}
