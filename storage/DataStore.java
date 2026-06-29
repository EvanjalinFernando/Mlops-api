package com.mlops.api.storage;

import com.mlops.api.models.MachineLearningModel;
import com.mlops.api.models.Workspace;
import com.mlops.api.models.EvaluationMetric;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class DataStore {

    public static Map<String, Workspace> workspaces = new HashMap<>();

    public static Map<String, MachineLearningModel> models = new HashMap<>();

    public static Map<String, List<EvaluationMetric>> metrics = new HashMap<>();
}