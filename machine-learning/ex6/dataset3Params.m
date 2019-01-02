function [C, sigma] = dataset3Params(X, y, Xval, yval)
%EX6PARAMS returns your choice of C and sigma for Part 3 of the exercise
%where you select the optimal (C, sigma) learning parameters to use for SVM
%with RBF kernel
%   [C, sigma] = EX6PARAMS(X, y, Xval, yval) returns your choice of C and
%   sigma. You should complete this function to return the optimal C and
%   sigma based on a cross-validation set.
%

% You need to return the following variables correctly.
C = 1;
sigma = 0.3;

% ====================== YOUR CODE HERE ======================
% Instructions: Fill in this function to return the optimal C and sigma
%               learning parameters found using the cross validation set.
%               You can use svmPredict to predict the labels on the cross
%               validation set. For example,
%                   predictions = svmPredict(model, Xval);
%               will return the predictions on the cross validation set.
%
%  Note: You can compute the prediction error using
%        mean(double(predictions ~= yval))
%

% params = [0.01; 0.03; 0.1];
params = [0.01; 0.03; 0.1; 0.3; 1; 3; 10; 30];
combinations = unique(nchoosek([params; params], 2), 'rows');
means = zeros(size(combinations,1), 1);

for i=1:size(combinations,1)
    C = combinations(i,1);
    sigma = combinations(i,2);

    model = svmTrain(X, y, C, @(x1, x2) gaussianKernel(x1, x2, sigma));
    predictions = svmPredict(model, Xval);

    err = mean(double(predictions ~= yval));
    means(i) = err;

    fprintf('C=%f, sigma=%f:  %f',C,sigma,err);
end

[m, in] = min(means);
C = combinations(in,1);
sigma = combinations(in,2);

fprintf('\nOptimal: C=%f, sigma=%f',C,sigma);

% =========================================================================

end
