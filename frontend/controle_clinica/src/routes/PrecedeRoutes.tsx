import type { JSX } from "react";
import { Navigate } from "react-router-dom";

interface Props {
  children: JSX.Element;
  isAllowed: boolean;
  redirectTo?: string;
}

export function ProtectedRoute({ children, isAllowed, redirectTo = "/" }: Props) {
  if (!isAllowed) {
    return <Navigate to={redirectTo} replace />;
  }
  return children;
}
