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

  private Configuration hBaseConf;

  public HBaseController(Configuration hBaseConf) {
    this.hBaseConf = hBaseConf;
  }

  @GetMapping(value = "/check")
  public String checkHBaseConnection() {
    try {
      HBaseAdmin.available(hBaseConf);
    } catch (MasterNotRunningException ex) {
      return "Master not running: " + ex.getMessage();
    } catch (ZooKeeperConnectionException ex) {
      return "Can not connect to Zookeeper: " + ex.getMessage();
    } catch (IOException ex) {
      return "IO Exception: " + ex.getMessage();
    }

    return "Connection is valid";
  }
}
