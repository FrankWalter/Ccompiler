#include <stdio.h>

double gaussian (const double& mean, const double& sigma) {
  double v1, v2;
  double s;
  double x;
  do
  {
    v1 = 2 * uniform(0, 1) -1;
    v2 = 2 * uniform(0, 1) -1;
    s = v1 * v1 + v2 * v2;
  }while (s >= 1.);
  x = v1 * (double) sqrt (-2. * log(s) /s);
  x = x * sigma + mean;
  return (x);
}

void high_dim_gaussian(const double* &mean, const double &sigma, const int& d, double* ans) {
  ans = new double[d];
  for (int i = 0; i < d; ++i) {
    ans[i] = gaussian(mean[i], sigma);
  }
}

int main(int argc, char** argv) {
  if (argc != 6) {
    printf("Usage: generate N n d maxx maxy");
    return 1;
  }

  int N = atoi(argv[1]);
  int n = atoi(argv[2]);
  int d = atoi(argv[3]);
  double maxx = atof(argv[4]);
  double maxy = atof(argv[5]);


  int per_num = N / n;
  for (int i = 0; i < n; ++i) {
    double* center = new double[d];
    
    delete center;
  }

  return 0;
}