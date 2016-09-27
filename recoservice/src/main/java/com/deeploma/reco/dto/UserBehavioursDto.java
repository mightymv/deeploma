package com.deeploma.reco.dto;

import java.util.List;

public class UserBehavioursDto {

		private Long userId;
		
		private Long time;
		
		private List<Long> prioMatches;

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public List<Long> getPrioMatches() {
			return prioMatches;
		}

		public void setPrioMatches(List<Long> prioMatches) {
			this.prioMatches = prioMatches;
		}

		public Long getTime() {
			return time;
		}

		public void setTime(Long time) {
			this.time = time;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((userId == null) ? 0 : userId.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			UserBehavioursDto other = (UserBehavioursDto) obj;
			if (userId == null) {
				if (other.userId != null)
					return false;
			} else if (!userId.equals(other.userId))
				return false;
			return true;
		}
		
		
}
