package initializer

import (
	"fmt"
	"hhub/connection-service/global"
	"os"
	"strings"

	"github.com/spf13/viper"
)

func AddConfiguration() {
	// Load environment setup here
	viper := viper.New()
	viper.AddConfigPath("./configs/")
	envName := getConfigFile()
	viper.SetConfigName(envName)
	viper.SetConfigType("yml")

	// Read the configuration file
	if err := viper.ReadInConfig(); err != nil {
		panic(fmt.Errorf("fatal error config file: %w", err))
	}
	
	//dynamic reading
	viper.AutomaticEnv()
	viper.SetEnvKeyReplacer(strings.NewReplacer(".", "_"))

	// Map to global settings
	if err := viper.Unmarshal(&global.Config); err != nil {
		panic(fmt.Errorf("fatal error config file: %w", err))
	}

}

func getConfigFile() string {
	env := os.Getenv("ENV")
	switch env {
	case "staging":
		return "staging"
	case "testing":
		return "testing"
	case "production":
		return "production"
	default:
		return "default"
	}
}
