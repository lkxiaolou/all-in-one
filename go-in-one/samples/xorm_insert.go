package main

import (
	"fmt"
	"github.com/go-xorm/xorm"
	"time"
     _ "github.com/go-sql-driver/mysql"
)

type Application struct {
	Id          int       `json:"id" xorm:"pk autoincr"`
	Name        string    `json:"name"`
	OdinNs      string    `json:"odinNs"`
	Owner       string    `json:"owner"`
	Description string    `json:"description"`
	ReleaseId   int       `json:"releaseId"`
	CreateTime  time.Time `json:"createTime" xorm:"created"`
	UpdateTime  time.Time `json:"updateTime" xorm:"updated"`
	Status      int       `json:"status"`
}

func main()  {
	engine, err := xorm.NewEngine("mysql", "root:123456@tcp(127.0.0.1:3306)/disfv4")
	if err != nil {
		fmt.Printf("NewEngine err %s\n", err.Error())
		return
	}

	app := &Application{
		Name: "lk-test",
		OdinNs: "test",
		Owner: "lk",
		Description: "lk test app",
		ReleaseId: 0,
		CreateTime: time.Now(),
		UpdateTime: time.Now(),
		Status: 0,
	}

	app2 := &Application{
		Name: "lk-test-1",
		OdinNs: "test",
		Owner: "lk",
		Description: "lk test app",
		ReleaseId: 0,
		CreateTime: time.Now(),
		UpdateTime: time.Now(),
		Status: 0,
	}

	apps := make([]*Application, 0)

	apps = append(apps, app)
	apps = append(apps, app2)

	affect, err := engine.Insert(apps)

	fmt.Printf("affect = %d, id = %d\n", affect, app.Id)
}