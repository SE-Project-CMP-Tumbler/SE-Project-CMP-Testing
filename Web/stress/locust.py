import time
from locust import HttpUser, task, between


class WebsiteUser(HttpUser):
  wait_time = between(1, 5)

  @task
  def visit_dashboard(self):
        self.client.get("/dashboard")


