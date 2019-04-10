package ua.edu.ukma.termpapers.controller;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hbase")
public class HBaseController {

  private Configuration hBaseConfiguration;

  public HBaseController(final Configuration hBaseConfiguration) {
    this.hBaseConfiguration = hBaseConfiguration;
  }

  @GetMapping(value = "/check")
  public String checkHBaseConnection() {
    try {
      HBaseAdmin.available(hBaseConfiguration);
    } catch (MasterNotRunningException ex) {
      return "Master not running";
    } catch (ZooKeeperConnectionException ex) {
      return "Can not connect to Zoo Keeper";
    } catch (IOException ex) {
      return "IO Exception";
    }

    return "Connection is valid";
  }
}
