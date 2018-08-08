#!/usr/bin/env bash

# Script to install a license file.

if [ "$(whoami)" == "root" ]; then
	echo -e "\033[38;5;196m"
	echo -e "Crafter CMS cowardly refuses to run as root."
	echo -e "Running as root is dangerous and is not supported."
	echo -e "\033[0m"
	exit 1
fi

export CRAFTER_HOME=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

# Execute Groovy script
"$CRAFTER_HOME/groovy/bin/groovy" -cp "$CRAFTER_HOME" -Dgrape.root="$CRAFTER_HOME" "$CRAFTER_HOME/install-license.groovy" "$@"
